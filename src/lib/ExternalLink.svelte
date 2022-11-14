<script lang="ts">
	export let href: string | undefined = '';
	export let target: string | undefined = '_blank';
</script>

<a href={href} target={target} rel="noreferrer"><slot /></a>

<style>
	a {
		text-decoration: underline;
		cursor: pointer;
	}

	a:hover {
		color: var(--hover-color);
		text-decoration: none;
	}
</style>
