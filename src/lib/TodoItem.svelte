<script lang="ts">
	export let href: string;
	import ExternalLink from '$lib/ExternalLink.svelte'
</script>

<ExternalLink href={href}>
	<p class="todo"><span>TODO: </span><slot /></p>
</ExternalLink>

<style lang="postcss">
	.todo {
		padding-bottom: 1em;
		margin-bottom: 1em;
		border-bottom: solid 1px;
		span {
			color: hsl(10 90% 55%);
		}
	}
</style>
