import * as core from '@actions/core'

export async function run(): Promise<void> {
    try {
        const whoToGreet: string = core.getInput('who-to-greet', {required: true})
        core.info(`Hello, ${whoToGreet}! WELCOME!`)

        const time: string = new Date().toTimeString()
        core.setOutput('time', time)
    } catch (error) {
        if (error instanceof Error) core.setFailed(error.message)
    }
}
