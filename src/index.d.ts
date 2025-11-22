/// <reference types="@sveltejs/kit" />

// See https://kit.svelte.dev/docs/types#app
// for information about these interfaces
declare namespace App {
	interface Locals {}

	interface PageData {}

	interface Platform {}
}

import type { VFile, VFileValue } from 'vfile';
import type { PathLike } from 'fs';

export interface BlogPost extends VFile {
	data: BlogPostMetadata;
	value: VFileValue;
}

export interface BlogPostMetadata extends VFileData {
	filepath?: PathLike;
	title?: string;
	tags?: string;
	subtitle?: string;
	id?: string;
	date?: string;
}
