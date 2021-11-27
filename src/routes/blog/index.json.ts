// src/routes/blog/index.json.ts
import fs from 'fs';
import path from 'path';
import { processOrg } from '$lib/process-org';

export function get() {
    const posts = fs
        .readdirSync(`src/routes/blog/_posts`, { encoding: 'utf8' })
        .filter((fileName) => (path.extname(fileName) == ".org"))
        .map((fileName) => {
            return processOrg(`src/routes/blog/_posts/${fileName}`)
        });

    return {
        body: {
            posts
        }
    };
};
