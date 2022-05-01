<script context="module">
  export async function load({ fetch }) {
    const res = await fetch(`/blog.json`);

    return {
      status: res.status,
      props: {
        blog: res.ok && (await res.json()),
      },
    };
  }
</script>

<script>
  export let blog;
  import BlogPreviewCard from "../../components/BlogPreviewCard.svelte";
</script>

<svelte:head>
  <title>Westrom.xyz - Blog Posts</title>
</svelte:head>

<h1>Blog</h1>
<div>
  <ol>
    {#each blog.posts as post}
      <li>
        <BlogPreviewCard {...post} />
      </li>
    {/each}
  </ol>
</div>

<style>
</style>
