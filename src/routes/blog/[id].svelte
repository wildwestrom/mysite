<script context="module">
  export async function load({ params, fetch }) {
    const id = params.id;
    const url = `/blog/${id}.json`;
    const res = await fetch(url);

    return {
      status: res.status,
      props: res.ok && (await res.json()),
    };
  }
</script>

<script>
  export let post;
</script>

<svelte:head>
  <title>{post.data.title} - Westrom.xyz - Blog</title>
  <meta name="description" content={post.data.description} />
</svelte:head>

<article>
  <h1 class="section-header">{post.data.title}</h1>
  {@html post.contents}
</article>

<style>
</style>
