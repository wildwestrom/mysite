<script lang="ts">
  export let post;
</script>

<a href="/blog/{post.data.id}">
  <div class="border border-2 rounded px-4">
    <h1>{post.data.title}</h1>
    <h2>{post.data.subtitle}</h2>
  </div>
</a>
