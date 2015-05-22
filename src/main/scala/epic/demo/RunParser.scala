package epic.demo

import jigg.util.IOUtil

object RunParser {
  def main(args: Array[String]) = {
    val sentences:IndexedSeq[IndexedSeq[String]] = IOUtil.openStandardIterator.toIndexedSeq.map(_.split("\\s+").toIndexedSeq)

    val parser = epic.models.ParserSelector.loadParser().get

    val before = System.currentTimeMillis

    sentences.par.map { s => (s, parser(s)) }.seq.foreach { case (s, p) => println(p render s) }

    // sentences.zipWithIndex foreach { case (sentence, i) =>
    //   // System.err.println(i + " " + sentence.mkString(" "))
    //   println(parser(sentence) render sentence)
    // }

    val parsingTime = System.currentTimeMillis - before

    val speed = sentences.size.toDouble / (parsingTime / 1000.0)

    System.err.println("parsing speed (sentence/sec): " + speed)
  }
}
