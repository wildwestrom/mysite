// src/lib/process-org.js
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

export function processOrg(filePath) {
  const processedFile = processor.processSync(
    fs.readFileSync(filePath, "utf8")
  );
  return processedFile;
}
