import { processOrg } from '$lib/process-org';

export async function get({ params }) {
  let { post } = params;

  const blogPost = processOrg(`src/posts/${post}.org`);

  return {
    status: 200,
    body: { blogPost }
  };
}
