import fs from 'fs';
import path from 'path';
import { unified } from 'unified';
import parse from 'uniorg-parse';
import { extractKeywords } from 'uniorg-extract-keywords';
import rehype from 'uniorg-rehype';
import rehypeRaw from 'rehype-raw';
import rehypeHighlight from 'rehype-highlight';
import type { Options as RehypeHighlightOptions } from 'rehype-highlight';
import rehypeStringify from 'rehype-stringify';
import rehypeShiftHeading from 'rehype-shift-heading';
import type { BlogPost } from 'src';
import langClojure from 'highlight.js/lib/languages/clojure';

const options: RehypeHighlightOptions = {
	languages: {
		clojure: langClojure
	}
};

const processor = unified()
	.use(parse)
	.use(extractKeywords)
	.use(rehype)
	.use(rehypeRaw)
	.use(rehypeHighlight, options)
	.use(rehypeShiftHeading, { shift: 1 })
	.use(rehypeStringify);

// Converts org document into HTML and JS.
export function processOrg(filePath: fs.PathLike): BlogPost {
	const org_document = fs.readFileSync(filePath, 'utf8');
	const processedDocument: BlogPost = processor.processSync(org_document);

	processedDocument.data.filePath = path.basename(filePath as string, '.org');

	return processedDocument;
}
