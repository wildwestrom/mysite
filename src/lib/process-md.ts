import fs from 'fs';
import path from 'path';
import type { VFile } from 'vfile';

import langClojure from 'highlight.js/lib/languages/clojure';
import type { Options as RehypeHighlightOptions } from 'rehype-highlight';

import yaml from 'yaml';

import { unified, type Plugin } from 'unified';
import type { Literal, Node, Parent } from 'unist';

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

function extractMetadataFromFrontmatter() {
	return (tree: Parent, file: VFile) => {
		const yamlNode = tree.children.find((node: Node) => node.type === 'yaml') as Literal;
		if (yamlNode) {
			file.data = yaml.parse(yamlNode.value as string);
		}
	};
}

const processor = unified()
	.use(remarkParse as Plugin)
	.use(remarkFrontmatter as Plugin)
	.use(extractMetadataFromFrontmatter)
	.use(remarkRehype)
	.use(rehypeRaw)
	.use(rehypeHighlight, options)
	.use(rehypeShiftHeading, { shift: 1 })
	.use(rehypeStringify);

import type { BlogPost } from '..';
// converts markdown document into html and js.
export function processMd(filepath: fs.PathLike): BlogPost {
	const markdown_doc = fs.readFileSync(filepath, 'utf8');
	const processed_document = processor.processSync(markdown_doc);

	processed_document.data.filepath = path.basename(filepath as string, '.md');

	return processed_document as BlogPost;
}
