<script lang="ts">
	interface Props {
		name: string;
		children?: import('svelte').Snippet;
	}

	let { name, children }: Props = $props();
</script>

<li>
	<h2>{name}</h2>
	{@render children?.()}
</li>

<style>
	li {
		margin-bottom: 0.75em;
	}
</style>
