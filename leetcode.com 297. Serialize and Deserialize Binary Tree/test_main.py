from unittest import TestCase
from main import Codec

class TestCodec(TestCase):
    def test1(self):
        ser = Codec()
        deser = Codec()
        data = '1,2,3,null,null,4,5,null,null,null,null'
        root = deser.deserialize(data)
        self.assertEqual(data, ser.serialize(root))
