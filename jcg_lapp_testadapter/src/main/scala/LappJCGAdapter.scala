import java.io.File

import nl.wvdzwan.lapp.Main


object LappJCGAdapter extends JCGTestAdapter {
  override def serializeCG(
                            algorithm: String,
                            target: String,
                            mainClass: String,
                            classPath: Array[String],
                            JDKPath: String,
                            analyzeJDK: Boolean,
                            outputFile: String
                          ): Long = {
    val before = System.nanoTime
    var after: Long = 0

    try {
      // Call graph
      val args = Array(
        "callgraph",
        "-o", "lapp_graph.buf",
        target
      )
      println(args.toList)
      Main.main(args)


      val mergeArgs = Array(
        "merge",
        "merged.buf",
        "lapp_graph.buf",
        "primordial.buf"
      )
      val primodialBuffer = new File("primodial.buf")
      if (primodialBuffer.exists) mergeArgs :+ primodialBuffer.getAbsoluteFile.toString
      println(mergeArgs.toList)
      Main.main(mergeArgs);

      // Flatten
      val flatArgs = Array(
        "flatten",
        "merged.buf",
        "lapp_flattened.buf"
      )
      println(flatArgs.toList)
      Main.main(flatArgs)

      after = System.nanoTime

      // Convert
      var convertArgs = Array(
        "convert",
        "jcg",
        "lapp_flattened.buf",
        outputFile
      )
      println(convertArgs.toList)
      Main.main(convertArgs)

    } catch {
      case e: Exception => println(e.getMessage)
    }

    after - before
  }

  override def possibleAlgorithms(): Array[String] = Array("RTA")

  override def frameworkName(): String = "Lapp"
}
