import reactX from 'eslint-plugin-react-x'
import reactDom from 'eslint-plugin-react-dom'
import { defineConfig } from 'eslint-define-config'

export default defineConfig([
  { files: ['**/*'], ignores: ['dist'] },
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      'eslint:recommended',
      'plugin:@typescript-eslint/recommended',
      'plugin:@typescript-eslint/recommended-requiring-type-checking',
      reactX.configs['recommended-typescript'],
      reactDom.configs.recommended,
      'plugin:react/recommended',
      'plugin:react-hooks/recommended',
      'prettier',
    ],
    parser: '@typescript-eslint/parser',
    parserOptions: {
      ecmaVersion: 2020,
      sourceType: 'module',
      ecmaFeatures: {
        jsx: true,
      },
      project: ['./tsconfig.json'],
    },
    settings: {
      react: {
        version: 'detect',
      },
    },
    rules: {
      '@typescript-eslint/explicit-function-return-type': 'warn',
      '@typescript-eslint/no-explicit-any': 'error',
      'react/prop-types': 'warn',
      'react/react-in-jsx-scope': 'warn',
    },
  },
])
