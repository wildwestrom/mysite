<script>
  export let post;
</script>

<a href="/blog/{post.data.id}">
  <div>
    <h2>{post.data.title}</h2>
    <h3>{post.data.subtitle}</h3>
  </div>
</a>

<style>
  a {
    div {
      margin: 0.5em 0;
      border-style: solid;
      border-width: 2px;
      border-radius: 0.25rem;
      padding-left: 1em;
      padding-right: 1em;
      h2 {
        line-height: 1.1;
      }
    }
  }
</style>
