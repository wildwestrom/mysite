<script lang="ts">
  export let post;
</script>

<a href="/blog/{post.data.id}">
  <div class="border rounded px-1">
    <h1>{post.data.title}</h1>
    <h2>{post.data.subtitle}</h2>
  </div>
</a>
