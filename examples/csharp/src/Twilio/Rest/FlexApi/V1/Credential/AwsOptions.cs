/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Accounts
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


using System;
using System.Collections.Generic;
using Twilio.Base;
using Twilio.Converters;




namespace Twilio.Rest.FlexApi.V1.Credential
{
    /// <summary> delete </summary>
    public class DeleteAwsOptions : IOptions<AwsResource>
    {
        
        
        public string PathSid { get; }



        /// <summary> Construct a new DeleteCredentialAwsOptions </summary>
        /// <param name="pathSid">  </param>
        public DeleteAwsOptions(string pathSid)
        {
            PathSid = pathSid;
        }

        
        /// <summary> Generate the necessary parameters </summary>
        public  List<KeyValuePair<string, string>> GetParams()
        {
            var p = new List<KeyValuePair<string, string>>();

            return p;
        }
        

    }


    /// <summary> fetch </summary>
    public class FetchAwsOptions : IOptions<AwsResource>
    {
    
        
        public string PathSid { get; }



        /// <summary> Construct a new FetchCredentialAwsOptions </summary>
        /// <param name="pathSid">  </param>
        public FetchAwsOptions(string pathSid)
        {
            PathSid = pathSid;
        }

        
        /// <summary> Generate the necessary parameters </summary>
        public  List<KeyValuePair<string, string>> GetParams()
        {
            var p = new List<KeyValuePair<string, string>>();

            return p;
        }
        

    }


    /// <summary> read </summary>
    public class ReadAwsOptions : ReadOptions<AwsResource>
    {
    



        
        /// <summary> Generate the necessary parameters </summary>
        public  override List<KeyValuePair<string, string>> GetParams()
        {
            var p = new List<KeyValuePair<string, string>>();

            if (PageSize != null)
            {
                p.Add(new KeyValuePair<string, string>("PageSize", PageSize.ToString()));
            }
            return p;
        }
        

    }

    /// <summary> update </summary>
    public class UpdateAwsOptions : IOptions<AwsResource>
    {
    
        
        public string PathSid { get; }

        
        public string TestString { get; set; }

        
        public bool? TestBoolean { get; set; }



        /// <summary> Construct a new UpdateCredentialAwsOptions </summary>
        /// <param name="pathSid">  </param>
        public UpdateAwsOptions(string pathSid)
        {
            PathSid = pathSid;
        }

        
        /// <summary> Generate the necessary parameters </summary>
        public  List<KeyValuePair<string, string>> GetParams()
        {
            var p = new List<KeyValuePair<string, string>>();

            if (TestString != null)
            {
                p.Add(new KeyValuePair<string, string>("TestString", TestString));
            }
            if (TestBoolean != null)
            {
                p.Add(new KeyValuePair<string, string>("TestBoolean", TestBoolean.Value.ToString().ToLower()));
            }
            return p;
        }
        

    }


}

