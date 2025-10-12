class Solution:
    def simplifyPath(self, path: str) -> str:
        tokens = path.split('/')

        answers = []
        for t in tokens:
            if '.' == t or t == '':
                continue
            elif '..' == t:
                answers = answers[:-1]
            else:
                answers.append(t)

        return '/' + '/'.join(answers)