import fs from 'fs'
import path from 'path'
import { processOrg } from '$lib/process-org'
import type { BlogPost } from 'src'

export function get(): { status: number; body: BlogPost[] } {
  const posts = fs
    .readdirSync(`static/org`)
    .filter(fileName => path.extname(fileName) == '.org')
    .map(fileName => {
      const post = processOrg(`static/org/${fileName}`)
      return post
    })
    .reverse()

  return {
    status: 200,
    body: posts
  }
}
