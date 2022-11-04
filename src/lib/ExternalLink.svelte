<script lang="ts">
	export let link: string;
</script>

<a href={link} target="_blank" rel="noreferrer">
	<slot />
</a>

<style>
	a {
		text-decoration: underline;
	}

	a:hover {
		color: var(--hover-color);
		text-decoration: none;
	}
</style>
