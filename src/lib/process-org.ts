// src/lib/process-org.ts
// filesystem access
import fs from 'fs';

// Uniorg stuff for parsing org text
import unified from 'unified';
import parse from 'uniorg-parse';
import { extractKeywords } from 'uniorg-extract-keywords';
import html from 'uniorg-rehype';
import raw from 'rehype-raw';
import highlight from 'rehype-highlight';
import stringify from 'rehype-stringify';

const processor = unified()
  .use(parse)
  .use(extractKeywords)
  .use(html)
  .use(raw)
  .use(highlight)
  .use(stringify);

export function processOrg(filePath: fs.PathOrFileDescriptor) {
  const processedFile = processor.processSync(fs.readFileSync(filePath, 'utf8'));
  return processedFile;
}
