<script lang="ts">
	export let href: string;
	import ExternalLink from '$lib/ExternalLink.svelte';
</script>

<ExternalLink {href}>
	<p class="todo"><span>TODO: </span><slot /></p>
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
