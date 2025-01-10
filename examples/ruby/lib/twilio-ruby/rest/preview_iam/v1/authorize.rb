##
#    This code was generated by
#    ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
#     |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
#     |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
#
#    Organization Public API
#    No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
#
#    NOTE: This class is auto generated by OpenAPI Generator.
#    https://openapi-generator.tech
#    Do not edit the class manually.
#

module Twilio
  module REST
    class PreviewIam < PreviewIamBase
      class V1 < Version
        class AuthorizeList < ListResource
          ##
          # Initialize the AuthorizeList
          # @param [Version] version Version that contains the resource
          # @return [AuthorizeList] AuthorizeList
          def initialize(version)
            super(version)
            # Path Solution
            @solution = {}
            @uri = "/authorize"
          end

          ##
          # Fetch the AuthorizeInstance
          # @param [String] response_type Response Type
          # @param [String] client_id The Client Identifier
          # @param [String] redirect_uri The url to which response will be redirected to
          # @param [String] scope The scope of the access request
          # @param [String] state An opaque value which can be used to maintain state between the request and callback
          # @return [AuthorizeInstance] Fetched AuthorizeInstance
          def fetch(
            response_type: :unset,
            client_id: :unset,
            redirect_uri: :unset,
            scope: :unset,
            state: :unset
          )

            params = Twilio::Values.of({
                                         'response_type' => response_type,
                                         'client_id' => client_id,
                                         'redirect_uri' => redirect_uri,
                                         'scope' => scope,
                                         'state' => state,
                                       })
            headers = Twilio::Values.of({ 'Content-Type' => 'application/x-www-form-urlencoded', })

            payload = @version.fetch('GET', @uri, params: params, headers: headers)
            AuthorizeInstance.new(
              @version,
              payload,
            )
          end

          # Provide a user friendly representation
          def to_s
            '#<Twilio.PreviewIam.V1.AuthorizeList>'
          end
        end

        class AuthorizePage < Page
          ##
          # Initialize the AuthorizePage
          # @param [Version] version Version that contains the resource
          # @param [Response] response Response from the API
          # @param [Hash] solution Path solution for the resource
          # @return [AuthorizePage] AuthorizePage
          def initialize(version, response, solution)
            super(version, response)

            # Path Solution
            @solution = solution
          end

          ##
          # Build an instance of AuthorizeInstance
          # @param [Hash] payload Payload response from the API
          # @return [AuthorizeInstance] AuthorizeInstance
          def get_instance(payload)
            AuthorizeInstance.new(@version, payload)
          end

          ##
          # Provide a user friendly representation
          def to_s
            '<Twilio.PreviewIam.V1.AuthorizePage>'
          end
        end

        class AuthorizeInstance < InstanceResource
          ##
          # Initialize the AuthorizeInstance
          # @param [Version] version Version that contains the resource
          # @param [Hash] payload payload that contains response from Twilio
          # @param [String] account_sid The SID of the
          #   {Account}[https://www.twilio.com/docs/iam/api/account] that created this Authorize
          #   resource.
          # @param [String] sid The SID of the Call resource to fetch.
          # @return [AuthorizeInstance] AuthorizeInstance
          def initialize(version, payload)
            super(version)

            # Marshaled Properties
            @properties = {
              'redirect_to' => payload['redirect_to'],
            }
          end

          ##
          # @return [String] The callback URL
          def redirect_to
            @properties['redirect_to']
          end

          ##
          # Provide a user friendly representation
          def to_s
            "<Twilio.PreviewIam.V1.AuthorizeInstance>"
          end

          ##
          # Provide a detailed, user friendly representation
          def inspect
            "<Twilio.PreviewIam.V1.AuthorizeInstance>"
          end
        end
      end
    end
  end
end