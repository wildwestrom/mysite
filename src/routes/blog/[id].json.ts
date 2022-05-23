import fs from 'fs'
import path from 'path'
import { processOrg } from '$lib/process-org'
import type { LoadInput } from '@sveltejs/kit'
import type { BlogPost } from 'src'

export async function get({
  params
}: LoadInput): Promise<{ status: number; body: BlogPost }> {
  const id = params.id
  const posts = fs
    .readdirSync(`static/org`)
    .filter(fileName => path.extname(fileName) == '.org')
    .map(fileName => {
      return processOrg(`static/org/${fileName}`)
    })

  const blogPost = posts.filter((post): boolean => {
    const idToBeMatched = post.data.filePath
    return id == idToBeMatched
  })[0]

  return {
    status: 200,
    body: blogPost
  }
}
