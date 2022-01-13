<script>
  export let post;
</script>

<a href="/blog/{post.data.id}">
  <div>
    <h1>{post.data.title}</h1>
    <h2>{post.data.subtitle}</h2>
  </div>
</a>

<style>
  a {
    div {
      max-width: 60ch;
      border-style: solid;
      border-width: 2px;
      border-radius: 0.25rem;
      padding-left: 1em;
      padding-right: 1em;
      h1 {
        font-size: 2em;
      }
      h2 {
        font-size: 1.25em;
        font-weight: 400;
      }
    }
  }
</style>
