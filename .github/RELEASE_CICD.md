# Release to MavenCentral from local machine

## Steps:

### Prerequisites

  1. Generate key: `gpg --full-generate-key`
  2. Check key id: `gpg --list-signatures`
  3. Upload to server: `gpg --keyserver hkps://keys.openpgp.org --send-keys [keyId]`
  4. Show the private key: `gpg --armor --export-secret-keys [keyId]`

### Versioning

You can change the version and indicate if is release or snapshot in the next file:

- networkResponse/[gradle.properties](/networkResponse/gradle.properties)

#### Releases

Before create the pull request to sync develop with master, change the project version and set 
isNetworkResponseRelease to true. Then create the pull request.

#### Snapshots

Before create the pull request to develop, change the project version and set 
`isNetworkResponseRelease` to false. 

Automatically, the version generated include a timestamp and the suffix `-SNAPSHOT`.
      
### Configure CI/CD (GitHub)

#### GitHub secrets

  - KeyId as `gpgKeyName`
  - Passphrase as `gpgPassphrase`
  - Private key as `gpgKey`
    1. Get the key using the command from point 4 in prerequisites.
    2. Replace all newlines with `\n`
  - Sonatype - Nexus user as `ossUser`
  - Sonatype - Nexus token as `ossToken`

#### GitHub workflow jobs

```
jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Maven publish
        env:
          ossUser: ${{ secrets.ossUser }}
          ossPass: ${{ secrets.ossPass }}
          gpgKeyName: ${{ secrets.gpgKeyName }}
          gpgPassphrase: ${{ secrets.gpgPassphrase }}
        run: ./gradlew publishAllPublicationsToMavenRepository -Psigning.gnupg.keyName=${{ secrets.gpgKeyName }} -Psigning.gnupg.passphrase=${{ secrets.gpgPassphrase }}
```
