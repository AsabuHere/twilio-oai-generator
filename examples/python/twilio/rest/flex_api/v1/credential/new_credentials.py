r"""
    This code was generated by
   ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
    |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
    |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \

    Twilio - Accounts
    This is the public Twilio REST API.

    NOTE: This class is auto generated by OpenAPI Generator.
    https://openapi-generator.tech
    Do not edit the class manually.
"""


from datetime import date, datetime
from typing import Any, Dict, List, Optional, Union
from twilio.base import deserialize, serialize, values

from twilio.base.instance_resource import InstanceResource
from twilio.base.list_resource import ListResource
from twilio.base.version import Version


class NewCredentialsInstance(InstanceResource):

    """
    :ivar account_sid:
    :ivar sid:
    :ivar test_string:
    :ivar test_integer:
    """

    def __init__(self, version: Version, payload: Dict[str, Any]):
        super().__init__(version)

        self.account_sid: Optional[str] = payload.get("account_sid")
        self.sid: Optional[str] = payload.get("sid")
        self.test_string: Optional[str] = payload.get("test_string")
        self.test_integer: Optional[int] = deserialize.integer(
            payload.get("test_integer")
        )

    def __repr__(self) -> str:
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        """

        return "<Twilio.FlexApi.V1.NewCredentialsInstance>"


class NewCredentialsList(ListResource):
    def __init__(self, version: Version):
        """
        Initialize the NewCredentialsList

        :param version: Version that contains the resource

        """
        super().__init__(version)

        self._uri = "/Credentials/AWS"

    def create(
        self,
        test_string: str,
        test_boolean: Union[bool, object] = values.unset,
        test_integer: Union[int, object] = values.unset,
        test_number: Union[float, object] = values.unset,
        test_number_float: Union[float, object] = values.unset,
        test_number_double: Union[float, object] = values.unset,
        test_number_int32: Union[float, object] = values.unset,
        test_number_int64: Union[int, object] = values.unset,
        test_object: Union[Dict[str, object], object] = values.unset,
        test_date_time: Union[datetime, object] = values.unset,
        test_date: Union[date, object] = values.unset,
        test_enum: Union["NewCredentialsInstance.Status", object] = values.unset,
        test_object_array: Union[List[object], object] = values.unset,
        test_any_type: Union[object, object] = values.unset,
        test_any_array: Union[List[object], object] = values.unset,
        permissions: Union[List[str], object] = values.unset,
        some_a2p_thing: Union[str, object] = values.unset,
    ) -> NewCredentialsInstance:
        """
        Create the NewCredentialsInstance

        :param test_string:
        :param test_boolean:
        :param test_integer:
        :param test_number:
        :param test_number_float:
        :param test_number_double:
        :param test_number_int32:
        :param test_number_int64:
        :param test_object:
        :param test_date_time:
        :param test_date:
        :param test_enum:
        :param test_object_array:
        :param test_any_type:
        :param test_any_array:
        :param permissions: A comma-separated list of the permissions you will request from the users of this ConnectApp.  Can include: `get-all` and `post-all`.
        :param some_a2p_thing:

        :returns: The created NewCredentialsInstance
        """
        data = values.of(
            {
                "TestString": test_string,
                "TestBoolean": test_boolean,
                "TestInteger": test_integer,
                "TestNumber": test_number,
                "TestNumberFloat": test_number_float,
                "TestNumberDouble": test_number_double,
                "TestNumberInt32": test_number_int32,
                "TestNumberInt64": test_number_int64,
                "TestObject": serialize.object(test_object),
                "TestDateTime": serialize.iso8601_datetime(test_date_time),
                "TestDate": serialize.iso8601_date(test_date),
                "TestEnum": test_enum,
                "TestObjectArray": serialize.map(
                    test_object_array, lambda e: serialize.object(e)
                ),
                "TestAnyType": serialize.object(test_any_type),
                "TestAnyArray": serialize.map(
                    test_any_array, lambda e: serialize.object(e)
                ),
                "Permissions": serialize.map(permissions, lambda e: e),
                "SomeA2PThing": some_a2p_thing,
            }
        )

        payload = self._version.create(
            method="POST",
            uri=self._uri,
            data=data,
        )

        return NewCredentialsInstance(self._version, payload)

    async def create_async(
        self,
        test_string: str,
        test_boolean: Union[bool, object] = values.unset,
        test_integer: Union[int, object] = values.unset,
        test_number: Union[float, object] = values.unset,
        test_number_float: Union[float, object] = values.unset,
        test_number_double: Union[float, object] = values.unset,
        test_number_int32: Union[float, object] = values.unset,
        test_number_int64: Union[int, object] = values.unset,
        test_object: Union[Dict[str, object], object] = values.unset,
        test_date_time: Union[datetime, object] = values.unset,
        test_date: Union[date, object] = values.unset,
        test_enum: Union["NewCredentialsInstance.Status", object] = values.unset,
        test_object_array: Union[List[object], object] = values.unset,
        test_any_type: Union[object, object] = values.unset,
        test_any_array: Union[List[object], object] = values.unset,
        permissions: Union[List[str], object] = values.unset,
        some_a2p_thing: Union[str, object] = values.unset,
    ) -> NewCredentialsInstance:
        """
        Asynchronously create the NewCredentialsInstance

        :param test_string:
        :param test_boolean:
        :param test_integer:
        :param test_number:
        :param test_number_float:
        :param test_number_double:
        :param test_number_int32:
        :param test_number_int64:
        :param test_object:
        :param test_date_time:
        :param test_date:
        :param test_enum:
        :param test_object_array:
        :param test_any_type:
        :param test_any_array:
        :param permissions: A comma-separated list of the permissions you will request from the users of this ConnectApp.  Can include: `get-all` and `post-all`.
        :param some_a2p_thing:

        :returns: The created NewCredentialsInstance
        """
        data = values.of(
            {
                "TestString": test_string,
                "TestBoolean": test_boolean,
                "TestInteger": test_integer,
                "TestNumber": test_number,
                "TestNumberFloat": test_number_float,
                "TestNumberDouble": test_number_double,
                "TestNumberInt32": test_number_int32,
                "TestNumberInt64": test_number_int64,
                "TestObject": serialize.object(test_object),
                "TestDateTime": serialize.iso8601_datetime(test_date_time),
                "TestDate": serialize.iso8601_date(test_date),
                "TestEnum": test_enum,
                "TestObjectArray": serialize.map(
                    test_object_array, lambda e: serialize.object(e)
                ),
                "TestAnyType": serialize.object(test_any_type),
                "TestAnyArray": serialize.map(
                    test_any_array, lambda e: serialize.object(e)
                ),
                "Permissions": serialize.map(permissions, lambda e: e),
                "SomeA2PThing": some_a2p_thing,
            }
        )

        payload = await self._version.create_async(
            method="POST",
            uri=self._uri,
            data=data,
        )

        return NewCredentialsInstance(self._version, payload)

    def __repr__(self) -> str:
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        """
        return "<Twilio.FlexApi.V1.NewCredentialsList>"
