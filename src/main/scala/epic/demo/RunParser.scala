package epic.demo

import jigg.util.IOUtil

object RunParser {
  def main(args: Array[String]) = {
    val sentences:IndexedSeq[IndexedSeq[String]] = IOUtil.openStandardIterator.toIndexedSeq.map(_.split("\\s+").toIndexedSeq)

    val parser = epic.models.ParserSelector.loadParser().get

    val before = System.currentTimeMillis

    sentences foreach { sentence =>
      println(parser(sentence) render sentence)
    }

    val parsingTime = System.currentTimeMillis - before

    val speed = sentences.size.toDouble / (parsingTime / 1000.0)

    System.err.println("parsing speed (sentence/sec): " + speed)
  }
}
