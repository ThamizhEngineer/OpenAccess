// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `angular-cli.json`.

export const environment = {
  production: false, 
  clientId: "OA",
  authApiUrl:'http://10.24.1.40/oa-auth-service/',
  // authApiUrl:'http://localhost:4212/oa-auth-service/', 
  //serviceApiUrl:'http://35.185.177.10/oa-service/',
  serviceApiUrl:'http://10.24.1.40/oa-service/',
  docApiUrl:'http://10.24.1.40/oa-document-service/',
  reportUrl:'http://10.24.1.40/oa-report-service/',
  assetsApiUrl:'http://localhost:4200',
  cryptoKey:'openaccesstneb12',
  month:'06',
};
