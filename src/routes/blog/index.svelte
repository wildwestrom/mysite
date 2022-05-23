<script lang="ts" context="module">
  import type { LoadInput } from '@sveltejs/kit'
  import type { BlogPost } from 'src'

  export async function load({
    fetch
  }: LoadInput): Promise<{ status: number; props: { blogPosts: BlogPost[] } }> {
    const res = await fetch(`/blog.json`)

    return {
      status: res.status,
      props: {
        blogPosts: await res.json()
      }
    }
  }
</script>

<script lang="ts">
  export let blogPosts: BlogPost[]
  import BlogPreviewCard from '../../components/BlogPreviewCard.svelte'
</script>

<svelte:head>
  <title>Westrom.xyz - Blog Posts</title>
</svelte:head>

<h1>Blog</h1>
<div>
  <ol>
    {#each blogPosts as post}
      <li>
        <BlogPreviewCard blogPost={post} />
      </li>
    {/each}
  </ol>
</div>

<style>
</style>
