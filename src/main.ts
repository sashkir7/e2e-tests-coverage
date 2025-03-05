import * as core from '@actions/core'
import parseAndroidRawSources from './parsesources'
import parseAndroidTestResults from './parsetestresults'

export async function run(): Promise<void> {
  try {
    const sourcesPath: string = core.getInput('sources-path', {
      required: true
    })
    const resultsPath: string = core.getInput('results-path', {
      required: true
    })

    core.info(`sourcesPath = ` + sourcesPath)
    core.info(`resultsPath = ` + resultsPath)

    await core.summary
      .addHeading('HEADING')
      .addDetails('LABEL', 'CONTENT')
      .addRaw('RAW')
      .addSeparator()
      .addLink('LINK', 'https://ya.ru')

    core.info('TEST TEXT')

    // justDoIt(sourcesPath, resultsPath)
  } catch (error) {
    if (error instanceof Error) core.setFailed(error.message)
  }
}

function justDoIt(sourcesPath: string, resultsPath: string) {
  let rawCoverage: Map<string, Set<string>> = parseAndroidRawSources(
    sourcesPath
  )
  let testsCoverage: Map<string, Set<string>> = parseAndroidTestResults(
    resultsPath
  )

  let sumPercents: number = 0
  let percents: Map<string, number> = new Map<string, number>()
  rawCoverage.forEach((parameters: Set<string>, className: string): void => {
    let testsElements: Set<string> = new Set()
    if (testsCoverage.has(className)) {
      testsElements = testsCoverage.get(className)
    }

    let percent: number = (testsElements.size * 100) / parameters.size
    sumPercents = sumPercents + percent
    percents.set(className, percent)
  })

  let averagePercent: number = sumPercents / percents.size

  console.log(percents)
  console.log('Средний % = ' + averagePercent)

  core.info(`Средний процент ` + averagePercent)
  core.info('-------')
  core.info(JSON.stringify(Object.fromEntries(percents)))
}
