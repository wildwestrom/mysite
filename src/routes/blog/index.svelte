<!-- src/routes/blog/index.svelte -->
<script lang="ts" context="module">
  /** @type {import('@sveltejs/kit').Load} */
  export async function load({ fetch }) {
    const res = await fetch(`/blog.json`);

    return {
      status: 200,
      props: {
        posts: await res.json()
      }
    };
  }
</script>

<script lang="ts">
  export let posts;
  // import BlogPreviewCard from '../../components/BlogPreviewCard.svelte';
</script>

<h1>Here are all my blog posts.</h1>
<pre>{posts}</pre>

<!--
     //{#each posts as post}
     //<BlogPreviewCard {...post} />
     //{/each}
-->

<svelte:head>
  <title>Westrom.xyz - Blog Posts</title>
</svelte:head>
