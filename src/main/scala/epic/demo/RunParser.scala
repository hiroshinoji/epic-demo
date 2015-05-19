package epic.demo

import jigg.util.IOUtil

object RunParser {
  def main(args: Array[String]) = {
    val sentences:IndexedSeq[IndexedSeq[String]] = IOUtil.openStandardIterator.toIndexedSeq.map(_.split("\\s+").toIndexedSeq)

    val parser = epic.models.ParserSelector.loadParser().get

    sentences foreach { sentence =>
      println(parser(sentence) render sentence)
    }
  }
}
