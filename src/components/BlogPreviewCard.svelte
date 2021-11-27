<script>
  export let post;
</script>

<a href="/blog/{post.id}">
  <div class="border rounded px-1">
    <h1>{post.title}</h1>
    <h2>{post.subtitle}</h2>
  </div>
</a>
