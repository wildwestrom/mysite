// filesystem access
import fs from "fs";

// Uniorg stuff for parsing org text
import unified from "unified";
import parse from "uniorg-parse";
import { extractKeywords } from "uniorg-extract-keywords";
import rehype from "uniorg-rehype";
import raw from "rehype-raw";
import highlight from "rehype-highlight";
import html from "rehype-stringify";

// HighlightJS
import langClojure from "highlight.js/lib/languages/clojure";
const languages = {
  clojure: langClojure,
};

const processor = unified()
  .use(parse)
  .use(extractKeywords)
  .use(rehype)
  .use(raw)
  .use(highlight, { languages: languages })
  .use(html);

// Converts org document into HTML and JS.
export function processOrg(filePath) {
  const org_document = fs.readFileSync(filePath, "utf8");

  const processed_document = processor.processSync(org_document);

  return processed_document;
}
