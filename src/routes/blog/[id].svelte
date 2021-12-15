<script lang="ts" context="module">
  export async function load(context) {
    const { id } = context.page.params;
    const res = await fetch(`/blog/${id}.json`);

    return {
      status: 200,
      props: {
        post: await res.json()
      }
    };
  }
</script>

<script>
  export let post;
  console.log(post.post);
</script>

<pre>{post.post.toString()}</pre>

<!-- <svelte:head>
     <title>{post.data.title} - Westrom.xyz Blog</title>
     <meta name="description" content={post.data.description} />
     </svelte:head> -->

<!-- <article>
     <h1 class="section-header">{post.data.title}</h1>
     {@html post.content}
     </article> -->
