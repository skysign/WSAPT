class Solution:
    def simplifyPath(self, path: str) -> str:
        tmps = path.split('/')
        tokens = []
        for t in tmps:
            if len(t) > 0:
                tokens.append(t)

        answers = []
        for t in tokens:
            if '.' == t:
                continue
            elif '..' == t:
                answers = answers[:-1]
            else:
                answers.append(t)

        ans = '/'.join(answers)

        return '/' + ans