/// <reference types="@sveltejs/kit" />

// See https://kit.svelte.dev/docs/types#app
// for information about these interfaces
declare namespace App {}

import type { VFile } from 'vfile'

export interface BlogPost extends VFile {
	data: any
}
