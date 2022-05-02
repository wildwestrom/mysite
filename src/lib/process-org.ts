// filesystem access
import fs from 'fs'

// Uniorg stuff for parsing org text
import { unified } from 'unified'
import parse from 'uniorg-parse'
import { extractKeywords } from 'uniorg-extract-keywords'
import rehype from 'uniorg-rehype'
import rehypeRaw from 'rehype-raw'
import rehypeHighlight from 'rehype-highlight'
import rehypeStringify from 'rehype-stringify'
import rehypeShiftHeading from 'rehype-shift-heading'

// HighlightJS
import langClojure from 'highlight.js/lib/languages/clojure'
import type { BlogPost } from 'src'
const languages = {
	clojure: langClojure
}

const processor = unified()
	.use(parse)
	.use(extractKeywords)
	.use(rehype)
	.use(rehypeRaw)
	.use(rehypeHighlight, { languages: languages })
	.use(rehypeShiftHeading, { shift: 1 })
	.use(rehypeStringify)

// Converts org document into HTML and JS.
export function processOrg(filePath: fs.PathLike): BlogPost {
	const org_document = fs.readFileSync(filePath, 'utf8')
	const processedDocument = processor.processSync(org_document)

	return processedDocument
}
