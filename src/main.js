import * as core from '@actions/core'

export async function run() {
    try {
        const whoToGreet = core.getInput('who-to-greet', {required: true})
        core.info(`Hello, ${whoToGreet}!`)

        // Get the current time and set as an output
        const time = new Date().toTimeString()
        core.setOutput('time', time)

    } catch (error) {
        core.setFailed(error.message)
    }
}
