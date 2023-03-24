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


from datetime import datetime
from decimal import Decimal
from typing import List, Optional
from twilio.base import deserialize, serialize, values
from twilio.base.instance_context import InstanceContext
from twilio.base.instance_resource import InstanceResource
from twilio.base.list_resource import ListResource
from twilio.base.version import Version


class FeedbackCallSummaryInstance(InstanceResource):
    class Status(object):
        IN_PROGRESS = "in-progress"
        PAUSED = "paused"
        STOPPED = "stopped"
        PROCESSING = "processing"
        COMPLETED = "completed"
        ABSENT = "absent"

    def __init__(self, version, payload, account_sid: str, sid: Optional[str] = None):
        """
        Initialize the FeedbackCallSummaryInstance
        """
        super().__init__(version)

        self._properties = {
            "account_sid": payload.get("account_sid"),
            "sid": payload.get("sid"),
            "test_string": payload.get("test_string"),
            "test_integer": deserialize.integer(payload.get("test_integer")),
            "test_object": payload.get("test_object"),
            "test_date_time": deserialize.rfc2822_datetime(
                payload.get("test_date_time")
            ),
            "test_number": deserialize.decimal(payload.get("test_number")),
            "price_unit": payload.get("price_unit"),
            "test_number_float": payload.get("test_number_float"),
            "test_number_decimal": payload.get("test_number_decimal"),
            "test_enum": payload.get("test_enum"),
            "a2p_profile_bundle_sid": payload.get("a2p_profile_bundle_sid"),
            "test_array_of_integers": payload.get("test_array_of_integers"),
            "test_array_of_array_of_integers": payload.get(
                "test_array_of_array_of_integers"
            ),
            "test_array_of_objects": payload.get("test_array_of_objects"),
            "test_array_of_enum": payload.get("test_array_of_enum"),
        }

        self._solution = {
            "account_sid": account_sid,
            "sid": sid or self._properties["sid"],
        }
        self._context: Optional[FeedbackCallSummaryContext] = None

    @property
    def _proxy(self) -> "FeedbackCallSummaryContext":
        """
        Generate an instance context for the instance, the context is capable of
        performing various actions. All instance actions are proxied to the context

        :returns: FeedbackCallSummaryContext for this FeedbackCallSummaryInstance
        """
        if self._context is None:
            self._context = FeedbackCallSummaryContext(
                self._version,
                account_sid=self._solution["account_sid"],
                sid=self._solution["sid"],
            )
        return self._context

    @property
    def account_sid(self) -> str:
        """
        :returns:
        """
        return self._properties["account_sid"]

    @property
    def sid(self) -> str:
        """
        :returns:
        """
        return self._properties["sid"]

    @property
    def test_string(self) -> str:
        """
        :returns:
        """
        return self._properties["test_string"]

    @property
    def test_integer(self) -> int:
        """
        :returns:
        """
        return self._properties["test_integer"]

    @property
    def test_object(self) -> str:
        """
        :returns:
        """
        return self._properties["test_object"]

    @property
    def test_date_time(self) -> datetime:
        """
        :returns:
        """
        return self._properties["test_date_time"]

    @property
    def test_number(self) -> float:
        """
        :returns:
        """
        return self._properties["test_number"]

    @property
    def price_unit(self) -> str:
        """
        :returns:
        """
        return self._properties["price_unit"]

    @property
    def test_number_float(self) -> float:
        """
        :returns:
        """
        return self._properties["test_number_float"]

    @property
    def test_number_decimal(self) -> Decimal:
        """
        :returns:
        """
        return self._properties["test_number_decimal"]

    @property
    def test_enum(self) -> "FeedbackCallSummaryInstance.Status":
        """
        :returns:
        """
        return self._properties["test_enum"]

    @property
    def a2p_profile_bundle_sid(self) -> str:
        """
        :returns: A2P Messaging Profile Bundle BundleSid
        """
        return self._properties["a2p_profile_bundle_sid"]

    @property
    def test_array_of_integers(self) -> List[int]:
        """
        :returns:
        """
        return self._properties["test_array_of_integers"]

    @property
    def test_array_of_array_of_integers(self) -> List[List[int]]:
        """
        :returns:
        """
        return self._properties["test_array_of_array_of_integers"]

    @property
    def test_array_of_objects(self) -> List[str]:
        """
        :returns:
        """
        return self._properties["test_array_of_objects"]

    @property
    def test_array_of_enum(self) -> List["FeedbackCallSummaryInstance.Status"]:
        """
        :returns: Permissions authorized to the app
        """
        return self._properties["test_array_of_enum"]

    def update(
        self, end_date, start_date, account_sid=values.unset
    ) -> "FeedbackCallSummaryInstance":
        """
        Update the FeedbackCallSummaryInstance

        :param date end_date:
        :param date start_date:
        :param str account_sid:

        :returns: The updated FeedbackCallSummaryInstance
        """
        return self._proxy.update(
            end_date=end_date,
            start_date=start_date,
            account_sid=account_sid,
        )

    async def update_async(
        self, end_date, start_date, account_sid=values.unset
    ) -> "FeedbackCallSummaryInstance":
        """
        Asynchronous coroutine to update the FeedbackCallSummaryInstance

        :param date end_date:
        :param date start_date:
        :param str account_sid:

        :returns: The updated FeedbackCallSummaryInstance
        """
        return await self._proxy.update_async(
            end_date=end_date,
            start_date=start_date,
            account_sid=account_sid,
        )

    def __repr__(self) -> str:
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        """
        context = " ".join("{}={}".format(k, v) for k, v in self._solution.items())
        return "<Twilio.Api.V2010.FeedbackCallSummaryInstance {}>".format(context)


class FeedbackCallSummaryContext(InstanceContext):
    def __init__(self, version: Version, account_sid: str, sid: str):
        """
        Initialize the FeedbackCallSummaryContext

        :param version: Version that contains the resource
        :param account_sid:
        :param sid:
        """
        super().__init__(version)

        # Path Solution
        self._solution = {
            "account_sid": account_sid,
            "sid": sid,
        }
        self._uri = "/Accounts/{account_sid}/Calls/Feedback/Summary/{sid}.json".format(
            **self._solution
        )

    def update(
        self, end_date, start_date, account_sid=values.unset
    ) -> FeedbackCallSummaryInstance:
        """
        Update the FeedbackCallSummaryInstance

        :param date end_date:
        :param date start_date:
        :param str account_sid:

        :returns: The updated FeedbackCallSummaryInstance
        """
        data = values.of(
            {
                "EndDate": serialize.iso8601_date(end_date),
                "StartDate": serialize.iso8601_date(start_date),
                "AccountSid": account_sid,
            }
        )

        payload = self._version.update(
            method="POST",
            uri=self._uri,
            data=data,
        )

        return FeedbackCallSummaryInstance(
            self._version,
            payload,
            account_sid=self._solution["account_sid"],
            sid=self._solution["sid"],
        )

    async def update_async(
        self, end_date, start_date, account_sid=values.unset
    ) -> FeedbackCallSummaryInstance:
        """
        Asynchronous coroutine to update the FeedbackCallSummaryInstance

        :param date end_date:
        :param date start_date:
        :param str account_sid:

        :returns: The updated FeedbackCallSummaryInstance
        """
        data = values.of(
            {
                "EndDate": serialize.iso8601_date(end_date),
                "StartDate": serialize.iso8601_date(start_date),
                "AccountSid": account_sid,
            }
        )

        payload = await self._version.update_async(
            method="POST",
            uri=self._uri,
            data=data,
        )

        return FeedbackCallSummaryInstance(
            self._version,
            payload,
            account_sid=self._solution["account_sid"],
            sid=self._solution["sid"],
        )

    def __repr__(self) -> str:
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        """
        context = " ".join("{}={}".format(k, v) for k, v in self._solution.items())
        return "<Twilio.Api.V2010.FeedbackCallSummaryContext {}>".format(context)


class FeedbackCallSummaryList(ListResource):
    def __init__(self, version: Version, account_sid: str):
        """
        Initialize the FeedbackCallSummaryList

        :param version: Version that contains the resource
        :param account_sid:

        """
        super().__init__(version)

        # Path Solution
        self._solution = {
            "account_sid": account_sid,
        }

    def get(self, sid) -> FeedbackCallSummaryContext:
        """
        Constructs a FeedbackCallSummaryContext

        :param sid:
        """
        return FeedbackCallSummaryContext(
            self._version, account_sid=self._solution["account_sid"], sid=sid
        )

    def __call__(self, sid) -> FeedbackCallSummaryContext:
        """
        Constructs a FeedbackCallSummaryContext

        :param sid:
        """
        return FeedbackCallSummaryContext(
            self._version, account_sid=self._solution["account_sid"], sid=sid
        )

    def __repr__(self) -> str:
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        """
        return "<Twilio.Api.V2010.FeedbackCallSummaryList>"
