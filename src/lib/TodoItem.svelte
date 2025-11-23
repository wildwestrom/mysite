<script lang="ts">
	import ExternalLink from '$lib/ExternalLink.svelte';
	interface Props {
		href: string;
		children?: import('svelte').Snippet;
	}

	let { href, children }: Props = $props();
</script>

<ExternalLink {href}>
	<p class="todo"><span>TODO: </span>{@render children?.()}</p>
</ExternalLink>

<style>
	.todo {
		padding-bottom: 1em;
		margin-bottom: 1em;
		border-bottom: solid 1px;
	}

	.todo span {
		color: hsl(10 90% 55%);
	}
</style>
