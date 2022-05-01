<script>
  export let link;
</script>

<a href={link} target="_blank">
  <slot />
</a>

<style>
  a {
    text-decoration: underline;

    &:hover {
      color: var(--hover-color);
      text-decoration: none;
    }
  }
</style>
