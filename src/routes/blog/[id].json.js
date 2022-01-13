// src/routes/blog/[id].json.js
import fs from 'fs';
import path from 'path';
import { processOrg } from '$lib/process-org';

export async function get({ params }) {
  const id = params.id;
  const posts = fs
    .readdirSync(`src/routes/blog/_posts`)
    .filter((fileName) => path.extname(fileName) == '.org')
    .map((fileName) => {
      return processOrg(`src/routes/blog/_posts/${fileName}`);
    });

  const post = posts.filter((post) => {
    const idToBeMatched = post.data.id;
    return id == idToBeMatched;
  })[0];

  return {
    status: 200,
      body: {
          post: post
      }
  };
}
