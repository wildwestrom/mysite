/// <reference types="@sveltejs/kit" />

// See https://kit.svelte.dev/docs/types#app
// for information about these interfaces
declare namespace App {}

import type { VFile, VFileValue } from 'vfile'
import type { PathLike } from 'fs'

export interface BlogPost extends VFile {
  data: BlogPostMetadata
  value: VFileValue
}

export interface BlogPostMetadata extends VFileData {
  filePath?: PathLike
  title?: string
  filetags?: string
}
