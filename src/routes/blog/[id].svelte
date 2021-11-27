<script context="module">
  export async function load(context) {
    const { id } = context.page.params;
    const { post } = await fetch(`/blog/${id}.json`).then((res) => res.json());

    return {
      props: {
        post
      }
    };
  }
</script>

<script>
  export let post;
</script>

<svelte:head>
  <title>{post.metadata.title}</title>
  <meta name="description" content={post.metadata.description} />
</svelte:head>

<article>
  <h1 class="section-header">{post.metadata.title}</h1>
  {@html post.content}
</article>

<style></style>
