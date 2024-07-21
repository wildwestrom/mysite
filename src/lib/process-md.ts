import fs from 'fs';
import path from 'path';

import langClojure from 'highlight.js/lib/languages/clojure';
import type { Options as RehypeHighlightOptions } from 'rehype-highlight';

import yaml from 'yaml';

import { unified } from 'unified';
import remarkParse from 'remark-parse';
import remarkFrontmatter from 'remark-frontmatter';
import remarkRehype from 'remark-rehype';
import rehypeRaw from 'rehype-raw';
import rehypeHighlight from 'rehype-highlight';
import rehypeStringify from 'rehype-stringify';
import rehypeShiftHeading from 'rehype-shift-heading';

const options: RehypeHighlightOptions = {
	languages: {
		clojure: langClojure
	}
};

function extractMetadata() {
  return (tree: { children: any[]; }, file: { data: any; }) => {
    const yamlNode = tree.children.find(node => node.type === 'yaml')
    if (yamlNode) {
      file.data = yaml.parse(yamlNode.value)
    }
  }
}
const processor = unified()
	.use(remarkParse)
	.use(remarkFrontmatter, ['yaml'])
	.use(extractMetadata)
	.use(remarkRehype)
	.use(rehypeRaw)
	.use(rehypeHighlight, options)
	.use(rehypeShiftHeading, { shift: 1 })
	.use(rehypeStringify);

import type { BlogPost } from '..';
// converts markdown document into html and js.
export function processMd(filepath: fs.PathLike) {
	const markdown_doc = fs.readFileSync(filepath, 'utf8');
	const processed_document = processor.processSync(markdown_doc);

	processed_document.data.filepath = path.basename(filepath as string, ".md");

	return processed_document;
}
