<script context="module">
  export async function load({ fetch }) {
    const res = await fetch(`/blog.json`);

    return {
      status: res.status,
      props: {
        blog: res.ok && await res.json(),
      },
    };
  }
</script>

<script>
  export let blog;
  import BlogPreviewCard from "../../components/BlogPreviewCard.svelte";
</script>

<h1>Here are all my blog posts.</h1>
<div class="grid gap-2">
  <ol>
    {#each blog.posts as post}
      <li>
        <BlogPreviewCard {...post} />
      </li>
    {/each}
  </ol>
</div>

<svelte:head>
  <title>Westrom.xyz - Blog Posts</title>
</svelte:head>
