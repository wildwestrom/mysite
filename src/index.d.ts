/// <reference types="@sveltejs/kit" />

// See https://kit.svelte.dev/docs/types#app
// for information about these interfaces
declare namespace App {}

import type { VFile, VFileValue } from 'vfile'

export interface BlogPost extends VFile {
	data: BlogPostMetadata
	value: VFileValue
}

export interface BlogPostMetadata extends VFileData {
	id?: string
	title?: string
	description?: string
	subtitle?: string
}
