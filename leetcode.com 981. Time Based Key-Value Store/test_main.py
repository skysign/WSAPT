from unittest import TestCase
from main import TimeMap


class TestTimeMap(TestCase):
    def test1_set(self):
        tm = TimeMap()
        tm.__init__()

        methods = ["set", "get", "get", "set", "get", "get"]
        params = [["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
        expecteds = [None, "bar", "bar", None, "bar2", "bar2"]

        for idx in range(len(methods)):
            _method = methods[idx]
            _param = params[idx]
            _expected = expecteds[idx]

            if 'set' == _method:
                method_set = getattr(tm, _method)
                self.assertEqual(_expected, method_set(_param[0], _param[1], _param[2]))
            else:
                method_get = getattr(tm, _method)
                self.assertEqual(_expected, method_get(_param[0], _param[1]))


    def test43_set(self):
        tm = TimeMap()
        tm.__init__()

        methods = ["set","set","get","get","get","get","get"]
        params = [["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
        expecteds = [None, None, "", "high","high","low","low"]

        for idx in range(len(methods)):
            _method = methods[idx]
            _param = params[idx]
            _expected = expecteds[idx]

            if 'set' == _method:
                method_set = getattr(tm, _method)
                self.assertEqual(_expected, method_set(_param[0], _param[1], _param[2]))
            else:
                method_get = getattr(tm, _method)
                self.assertEqual(_expected, method_get(_param[0], _param[1]))


    def test49_set(self):
        tm = TimeMap()
        tm.__init__()

        methods = ["set","set","get","set","get","get"]
        params = [["a","bar",1],["x","b",3],["b",3],["foo","bar2",4],["foo",4],["foo",5]]
        expecteds = [None, None, "", None, "bar2", "bar2"]

        for idx in range(len(methods)):
            _method = methods[idx]
            _param = params[idx]
            _expected = expecteds[idx]

            if 'set' == _method:
                method_set = getattr(tm, _method)
                self.assertEqual(_expected, method_set(_param[0], _param[1], _param[2]))
            else:
                method_get = getattr(tm, _method)
                self.assertEqual(_expected, method_get(_param[0], _param[1]))
