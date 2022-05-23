<script lang="ts">
  import type { BlogPost } from 'src'
  export let blogPost: BlogPost
</script>

<a href="/blog/{blogPost.data.filePath}">
  <div>
    <h2>{blogPost.data.title}</h2>
  </div>
</a>

<style>
  a {
    div {
      margin: 0.5em;
      border-style: solid;
      border-width: 2px;
      border-radius: 0.5em;
      padding-left: 1em;
      padding-right: 1em;
      padding: 0.5em;

      h3 {
        font-weight: 400;
        line-height: 1.1;
      }
    }
  }
</style>
