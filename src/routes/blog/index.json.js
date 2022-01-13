// src/routes/blog/index.json.js
import fs from 'fs';
import path from 'path';
import { processOrg } from '$lib/process-org';

export function get() {
  const posts = fs
    .readdirSync(`src/routes/blog/_posts`)
    .filter((fileName) => path.extname(fileName) == '.org')
    .map((fileName) => {
      let post = processOrg(`src/routes/blog/_posts/${fileName}`);
      return {
        post
      };
    })
    .reverse();

  return {
    status: 200,
    body: {
      posts
    }
  };
}
